package uz.elmurodov.meal.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.elmurodov.meal.domain.AuthUser;
import uz.elmurodov.meal.dto.auth.AuthUserDto;
import uz.elmurodov.meal.dto.auth.SessionDto;
import uz.elmurodov.meal.dto.response.AppErrorDto;
import uz.elmurodov.meal.dto.response.DataDto;
import uz.elmurodov.meal.properties.ServerProperties;
import uz.elmurodov.meal.repository.AuthUserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthUserService implements AbstractService, UserDetailsService {

    private final AuthUserRepository authUserRepository;
    private final ServerProperties serverProperties;
    private final ObjectMapper objectMapper;

    public ResponseEntity<DataDto<SessionDto>> getToken(AuthUserDto dto) {

        try {

            HttpClient httpclient = HttpClientBuilder.create().build();
            HttpPost httppost = new HttpPost(serverProperties.getServerUrl() + "/api/login");
            byte[] bytes = objectMapper.writeValueAsBytes(dto);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded");
            httppost.setEntity(new InputStreamEntity(byteArrayInputStream));

            HttpResponse response = httpclient.execute(httppost);

            JsonNode json_auth = objectMapper.readTree(EntityUtils.toString(response.getEntity()));

            if (json_auth.has("success") && json_auth.get("success").asBoolean()) {
                JsonNode node = json_auth.get("data");
                SessionDto sessionDto = objectMapper.readValue(node.toString(), SessionDto.class);
                return new ResponseEntity<>(new DataDto<>(sessionDto), HttpStatus.OK);
            }
            return new ResponseEntity<>(new DataDto<>(objectMapper.readValue(json_auth.get("error").toString(),
                    AppErrorDto.class)), HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .message(e.getLocalizedMessage())
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build()), HttpStatus.OK);
        }
    }

    public ResponseEntity<DataDto<SessionDto>> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUser> user = authUserRepository.findByUsernameAndDeletedFalse(username);
        return User.builder()
                .username(user.get().getUsername())
                .password(user.get().getPassword())
                .authorities(user.get().getAuthority())
                .accountLocked(false)
                .accountExpired(false)
                .disabled(false)
                .credentialsExpired(false)
                .build();
    }
}
