package dev.java.odaat.Controller;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.LocalDateAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import dev.java.odaat.Entity.Note;
import dev.java.odaat.Service.NoteService;

@ActiveProfiles("test")
@WebMvcTest(NoteController.class)
@WithMockUser(username = "Tester", roles = "USER")
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    private static Note note1;
    private static Note note2;
    private static Note note3;

    private static ObjectMapper mapper;

    @BeforeAll
    static void setup(){

        note1 = new Note(1, LocalDate.of(2020, 8, 15), LocalDate.now(), "Mock Title 1", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Error, reiciendis? Repudiandae qui corrupti commodi ratione voluptatum sed nostrum itaque laudantium placeat, inventore explicabo sequi voluptatibus adipisci aperiam animi porro et aliquam, ipsam impedit aspernatur unde! Necessitatibus sunt recusandae quaerat beatae accusamus numquam incidunt laborum iure laboriosam veniam, nobis, ad eveniet, omnis quisquam quod non exercitationem. Fuga qui dolores nemo rerum incidunt animi perferendis harum aspernatur veniam. Id perspiciatis esse laborum atque debitis corporis eius porro. Quod tempora nemo ad inventore facere nisi, repudiandae quas eius labore accusamus in illo fuga culpa natus corrupti. Facere quos sunt qui, ex aliquid doloremque.");
        note2 = new Note(2, LocalDate.of(2023, 5, 1), LocalDate.of(2023, 5, 1), "Mock Title 2", "Lorem ipsum dolor sit amet consectetur adipisicing elit. Error, reiciendis? Repudiandae qui corrupti commodi ratione voluptatum sed nostrum itaque laudantium placeat, inventore explicabo sequi voluptatibus adipisci aperiam animi porro et aliquam, ipsam impedit aspernatur unde!");
        note3 = new Note(3, LocalDate.of(2021, 5, 1), LocalDate.of(2023, 2, 1), "Mock Title 3", "Lorem ipsum dolor sit amet consectetur adipisicing elit.");
        
        mapper = new ObjectMapper()
        .registerModule(new ParameterNamesModule())
        .registerModule(new Jdk8Module())
        .registerModule(new JavaTimeModule());
    }

    @Test
    void getNoteById() throws Exception{
        
        when(noteService.getOne(1)).thenReturn(note1);
        
        mockMvc
            .perform(get("/api/v1/note/get/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(note1.getId())))
            .andExpect(jsonPath("$.createdAt", is(note1.getCreatedAt().toString())))
            .andExpect(jsonPath("$.updatedAt", is(note1.getUpdatedAt().toString())))
            .andExpect(jsonPath("$.title", is(note1.getTitle())))
            .andExpect(jsonPath("$.content", is(note1.getContent())));

    }

    @Test
    void getAllNotes() throws Exception{
        List<Note> expected = List.of(note1, note2, note3);
        when(noteService.getAll()).thenReturn(expected);

        MvcResult result = mockMvc
            .perform(get("/api/v1/note/getAll"))
            .andExpect(status().isOk())
            .andReturn();

        verify(noteService).getAll();

    }

    // TODO: Test INSERT, UPDATE, DELETE.
}