//// File: MRTS/controller/UserControllerTest.java
//
//package MRTS.controller;
//
//import MRTS.DTO.LogInDto;
//import MRTS.DTO.UserDto;
//import MRTS.DTO.UserResponse;
//import MRTS.domain.UserType;
//import MRTS.security.JwtAuthenticationFilter;
//import MRTS.services.UserService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.hamcrest.Matcher;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.internal.matchers.Matches;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
//import org.springframework.test.context.event.annotation.BeforeTestClass;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static io.restassured.RestAssured.given;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@ExtendWith(MockitoExtension.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class UserControllerTest {
//
//}
//
//    @Test
//    @Order(1)
//    void shouldRegisterUser() {
//        // Prepare test data
//        UserDto userDto = new UserDto();
//        userDto.setUserFullName("Test User");
//        userDto.setUserEmail("
//
//
//}
////
////    @Autowired
////    private TestRestTemplate restTemplate; // For full E2E tests with HTTP requests
////
////    @Autowired
////    private ObjectMapper mapper;
////
////    @Autowired
////    private MockMvc mockMvc; // For direct MockMvc testing
////
////    @Test
////    void shouldRegisterUserEndToEnd() throws Exception {
////        // Prepare test data
////        UserDto userDto = new UserDto();
////        userDto.setUserFullName("E2E Test User");
////        userDto.setUserEmail("e2e_test@gmail.com");
////        userDto.setUserPassword("password123");
////        userDto.setUserStatus(true);
////        userDto.setRole(UserType.PATIENT);
////
////        // Perform POST request to /users/register
////        MvcResult result = mockMvc.perform(post("/users/register")
////                        .with(csrf())
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(mapper.writeValueAsString(userDto))
////                        .accept(MediaType.APPLICATION_JSON))
////                .andDo(MockMvcResultHandlers.print())
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$.userFullName").value("E2E Test User"))
////                .andExpect(jsonPath("$.userEmail").value("e2e_test@gmail.com"))
////                .andExpect(jsonPath("$.role").value("PATIENT"))
////                .andExpect(jsonPath("$.userStatus").value(true))
////                .andReturn();
////
////        // Assert response data
////        String responseContent = result.getResponse().getContentAsString();
////        UserResponse userResponse = mapper.readValue(responseContent, UserResponse.class);
////        assertNotNull(userResponse.getUserId());
////    }
////
//////    @BeforeTestClass
//////    public static void setup() {
//////        RestAssured.port = Integer.valueOf(8090);
//////        RestAssured.baseURI = "http://localhost/";
//////        RestAssured.basePath = "";
//////    }
//////    @Test
//////    public void testGetOneBook() {
//////        given()
//////                .when()
//////                .get("book/123")
//////                .then()
//////                .contentType(ContentType.JSON)
//////                .and()
//////                .body("isbn",equalTo("123"))
//////                .body("title",equalTo("Book 1"))
//////                .body("price",equalTo(20.95f))
//////                .body("author",equalTo("James Brown"));
////////        Use f for real numbers
//////    }
////
//////    private Matcher<?> equalTo(String number) {
//////    }
////
////    @Test
////    void shouldLogInUserEndToEnd() throws Exception {
////        // Prepare test data for login
////        LogInDto logInDto = new LogInDto();
////        logInDto.setUserEmail("e2e_test@gmail.com");
////        logInDto.setUserPassword("password123");
////
////        // Perform POST request to /users/login
////
////        MvcResult result = mockMvc.perform(post("/users/login")
////                        .with(csrf())
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(mapper.writeValueAsString(logInDto))
////                        .accept(MediaType.APPLICATION_JSON))
////                .andDo(MockMvcResultHandlers.print())
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$.userFullName").value("E2E Test User"))
////                .andReturn();
////
////        // Assert response data
////        String responseContent = result.getResponse().getContentAsString();
////        UserResponse userResponse = mapper.readValue(responseContent, UserResponse.class);
////        assertNotNull(userResponse.getJwtToken());
////    }
////}
