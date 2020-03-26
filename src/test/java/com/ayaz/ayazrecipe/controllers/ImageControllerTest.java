package com.ayaz.ayazrecipe.controllers;

import com.ayaz.ayazrecipe.Services.ImageService;
import com.ayaz.ayazrecipe.Services.RecipeService;
import com.ayaz.ayazrecipe.commands.RecipeCommand;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ImageControllerTest {
    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    ImageController imageController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        imageController = new ImageController(recipeService, imageService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();

    }

    @Test
    void showUploadForm() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);
        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"));
        verify(recipeService, times(1)).findCommandById(anyLong());
    }

    @Test
    void uploadForm() throws Exception {
        MockMultipartFile mockMultipartFile =
                new MockMultipartFile("imagefile", "testing.txt", "text/plain", "Mockito Test ".getBytes());
        mockMvc.perform(multipart("/recipe/1/image").file(mockMultipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location","/recipe/1/show"));
        verify(imageService,times(1)).saveImage(anyLong(),any());
    }

    @Test
    void renderImage() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(1L);

        String s = "fake image txt";
        Byte[] bytesBoxed = new Byte[s.getBytes().length];

        int i = 0;


        for (byte primByte: s.getBytes()){
            bytesBoxed[i++] = primByte;
        }
        command.setImage(bytesBoxed);
        when(recipeService.findCommandById(anyLong())).thenReturn(command);

        MockHttpServletResponse response = mockMvc.perform(get("/recipe/1/recipeimage"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        byte[] responseBytes = response.getContentAsByteArray();
        Assert.assertEquals(s.getBytes().length , responseBytes.length);

    }
}