package com.dquynh2207.jwt_project.configuration;


import com.dquynh2207.jwt_project.entity.User;
import com.dquynh2207.jwt_project.enums.Role;
import com.dquynh2207.jwt_project.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    private PasswordEncoder passwordEncoderBCRYPT;

    @Bean
    //Cái này sẽ đc chạy mỗi khi app start lên -> tạo 1 tk admin
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()){
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoderBCRYPT.encode("admin"))
                        //.roles(roles)
                        .build();
                userRepository.save(user);
                log.info("admin user has been create with default password: admin, please change it");
            }
        };
    }
}
