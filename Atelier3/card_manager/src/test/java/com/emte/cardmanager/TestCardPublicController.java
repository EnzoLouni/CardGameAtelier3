import com.emte.cardmanager.controller.CardPublicController;
import com.emte.cardmanager.dao.CardRepository;
import com.emte.cardmanager.service.CardService;
import com.emte.dto.CardDto;
import com.emte.mapper.CardMapper;
import com.emte.model.Card;
import com.emte.view.Views;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CardPublicControllerTest {

    @Mock
    private CardService cardService;

    private MockMvc mockMvc;
    private ObjectWriter objectWriter;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        CardPublicController cardPublicController = new CardPublicController(cardService);
        mockMvc = MockMvcBuilders.standaloneSetup(cardPublicController).build();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        objectMapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));
        objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    void getCard_ValidId_ReturnsCardDto() throws Exception {
        CardDto cardDto = new CardDto();
        cardDto.setId(1);
        cardDto.setName("Card 1");
        when(cardService.getCard(1)).thenReturn(cardDto);

        mockMvc.perform(get("/public/cards/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Card 1"));

        verify(cardService, times(1)).getCard(1);
        verifyNoMoreInteractions(cardService);
    }

    @Test
    void getCard_InvalidId_ReturnsNotFound() throws Exception {
        when(cardService.getCard(1)).thenThrow(new ResourceNotFoundException());

        mockMvc.perform(get("/public/cards/{id}", 1))
                .andExpect(status().isNotFound());

        verify(cardService, times(1)).getCard(1);
        verifyNoMoreInteractions(cardService);
    }

    @Test
    void updateCard_ValidIdAndCardDto_ReturnsUpdatedCardDto() throws Exception {
        CardDto cardDto = new CardDto();
        cardDto.setId(1);
        cardDto.setName("Updated Card 1");
        when(cardService.updateCard(eq(1), any(CardDto.class))).thenReturn(cardDto);

        mockMvc.perform(put("/public/cards/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(cardDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Card 1"));

        verify(cardService, times(1)).updateCard(eq(1), any(CardDto.class));
        verifyNoMoreInteractions(cardService);
    }

    @Test
    void deleteCard_ValidId_ReturnsTrue() throws Exception {
        when(cardService.deleteCard(1)).thenReturn(true);

        mockMvc.perform(delete("/public/cards/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));

        verify(cardService, times(1)).deleteCard(1);
        verifyNoMoreInteractions(cardService);
    }

    @Test
    void deleteCard_InvalidId_ReturnsNotFound() throws Exception {
        when(cardService.deleteCard(1)).thenThrow(new ResourceNotFoundException());

        mockMvc.perform(delete("/public/cards/{id}", 1))
                .andExpect(status().isNotFound());

        verify(cardService, times(1)).deleteCard(1);
        verifyNoMoreInteractions(cardService);
    }

    @Test
    void createCard_ValidCardDto_ReturnsCreatedCardDto() throws Exception {
        CardDto cardDto = new CardDto();
        cardDto.setId(1);
        cardDto.setName("New Card");
        when(cardService.createCard(any(CardDto.class))).thenReturn(cardDto);

        mockMvc.perform(post("/public/card")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectWriter.writeValueAsString(cardDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("New Card"));

        verify(cardService, times(1)).createCard(any(CardDto.class));
        verifyNoMoreInteractions(cardService);
    }

    @Test
    void getCardsToSell_ReturnsListOfCardDto() throws Exception {
        CardDto cardDto = new CardDto();
        cardDto.setId(1);
        cardDto.setName("Card 1");
        when(cardService.getCardsToSell()).thenReturn(Collections.singletonList(cardDto));

        mockMvc.perform(get("/public/cards_to_sell"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Card 1"));

        verify(cardService, times(1)).getCardsToSell();
        verifyNoMoreInteractions(cardService);
    }

    @Test
    void getCards_ReturnsListOfCardDto() throws Exception {
        CardDto cardDto1 = new CardDto();
        cardDto1.setId(1);
        cardDto1.setName("Card 1");
        CardDto cardDto2 = new CardDto();
        cardDto2.setId(2);
        cardDto2.setName("Card 2");
        when(cardService.getCards()).thenReturn(List.of(cardDto1, cardDto2));

        mockMvc.perform(get("/public/cards"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Card 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Card 2"));

        verify(cardService, times(1)).getCards();
        verifyNoMoreInteractions(cardService);
    }
}
