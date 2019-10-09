package com.ayaz.ayazrecipe.converters;

import com.ayaz.ayazrecipe.commands.NotesCommand;
import com.ayaz.ayazrecipe.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {
    @Override
    public Notes convert(NotesCommand notesCommand) {
        if (notesCommand != null) {
            final Notes notes = new Notes();
            notes.setId(notesCommand.getId());
            notes.setRecipeNotes(notesCommand.getRecipeNotes());
            return notes;
        }
        return null;
    }
}
