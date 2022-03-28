package uz.elmurodov.meal;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.elmurodov.meal.domain.AuthUser;
import uz.elmurodov.meal.enums.AuthRole;
import uz.elmurodov.meal.enums.Department;
import uz.elmurodov.meal.enums.Position;
import uz.elmurodov.meal.enums.Status;
import uz.elmurodov.meal.properties.OpenApiProperties;
import uz.elmurodov.meal.properties.ServerProperties;
import uz.elmurodov.meal.repository.AuthUserRepository;
import uz.elmurodov.meal.telegrambot.TelegramBot;

@SpringBootApplication
@EnableConfigurationProperties({
        OpenApiProperties.class,
        ServerProperties.class
})
@OpenAPIDefinition
@RequiredArgsConstructor
public class MealApplication {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MealApplication.class, args);
        TelegramBot telegramBot = context.getBean(TelegramBot.class);
        telegramBot.run();
    }


    //    @Bean
    CommandLineRunner runner() {
        return (args) -> {
            authUserRepository.deleteAll();
            String encode = passwordEncoder.encode("123");
            System.out.println("encode = " + encode);

            AuthUser admin = AuthUser.childBuilder()
                    .username("admin")
                    .password(encode)
                    .role(AuthRole.ADMIN)
                    .position(Position.CEO)
                    .status(Status.ACTIVE)
                    .department(Department.SELLS)
                    .fullName("adminov admin adminovich")
                    .phone("+998906543210")
                    .build();
            authUserRepository.save(admin);
        };
    }

}
