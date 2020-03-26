package com.ayaz.ayazrecipe.controllers;

import com.ayaz.ayazrecipe.Services.ImageService;
import com.ayaz.ayazrecipe.Services.RecipeService;
import com.ayaz.ayazrecipe.commands.RecipeCommand;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {

    private final RecipeService recipeService;
    private final ImageService imageService;

    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping("/recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/imageuploadform";
    }
    @PostMapping("/recipe/{id}/image")
    public String uploadForm(@PathVariable String id, @RequestParam("imagefile")MultipartFile multipartFile){
        imageService.saveImage(Long.valueOf(id),multipartFile);
        return "redirect:/recipe/" + id + "/show";
    }

    @GetMapping("recipe/{id}/recipeimage")
    public void renderImage(@PathVariable String id , HttpServletResponse response) throws IOException {
        RecipeCommand command = recipeService.findCommandById(Long.valueOf(id));
        byte[] arrayByte = new byte[command.getImage().length];

        int i = 0;

        for (Byte wrappedByte : command.getImage() ) {
            arrayByte[i++] = wrappedByte;
        }
        response.setContentType("image/jpeg");
        InputStream inputStream = new ByteArrayInputStream(arrayByte);
        IOUtils.copy(inputStream , response.getOutputStream());
    }
}
