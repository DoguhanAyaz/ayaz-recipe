package com.ayaz.ayazrecipe.converters;

import com.ayaz.ayazrecipe.commands.NotesCommand;
import com.ayaz.ayazrecipe.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {
    @Override
    public NotesCommand convert(final Notes notes) {
        if (notes != null){
            final NotesCommand notesCommand = new NotesCommand();
            notesCommand.setId(notes.getId());
            notesCommand.setRecipeNotes(notes.getRecipeNotes());
            return notesCommand;
        }
        return null;
    }
}
