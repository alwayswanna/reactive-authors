package agleb.databaseservice;


import agleb.databaseservice.model.dto.AccountDTO;
import agleb.databaseservice.model.dto.RoleDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mock;

    AccountDTO accountDTO = new AccountDTO(null, "checkUsernameTest", "checkUsernameTest",
            "CheckName", "CheckSurname",
            "check@ya.ru", true, RoleDTO.ROLE_ADMINISTRATOR, null);

    @Test
    public void addNewAccountTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(accountDTO);

        this.mock.perform(post("/db_service/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnListWithAccountTest() throws Exception {

        MvcResult result = mock.perform(get("/db_service/accounts"))
                .andExpect(status().isOk())
                .andReturn();

        ObjectMapper objectMapper = new ObjectMapper();
        List<AccountDTO> accountDTOS = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<AccountDTO>>() {
        });

        int index = accountDTOS.size() - 1;

        this.mock.perform(get("/db_service/accounts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[" + index + "].username", is(accountDTO.getUsername())))
                .andExpect(jsonPath("$[" + index + "].password", is(accountDTO.getPassword())))
                .andExpect(jsonPath("$[" + index + "].name", is(accountDTO.getName())))
                .andExpect(jsonPath("$[" + index + "].surname", is(accountDTO.getSurname())))
                .andExpect(jsonPath("$[" + index + "].email", is(accountDTO.getEmail())))
                .andExpect(jsonPath("$[" + index + "].active", is(accountDTO.isActive())))
        ;
    }

    @Test
    public void editAddedAccountTest() throws Exception{

    }

    @Test
    public void checkEditAccountTest() throws Exception{

    }

    @Test
    public void deleteAddedTestAccountTest() throws Exception{

    }

}
