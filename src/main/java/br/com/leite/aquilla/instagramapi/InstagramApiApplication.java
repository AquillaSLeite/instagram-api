package br.com.leite.aquilla.instagramapi;

import br.com.leite.aquilla.instagramapi.config.AwsConfig;
import br.com.leite.aquilla.instagramapi.entity.File;
import br.com.leite.aquilla.instagramapi.entity.Post;
import br.com.leite.aquilla.instagramapi.entity.User;
import br.com.leite.aquilla.instagramapi.enums.AccountTypeEnum;
import br.com.leite.aquilla.instagramapi.enums.GenderEnum;
import br.com.leite.aquilla.instagramapi.repository.FileRepository;
import br.com.leite.aquilla.instagramapi.repository.PostRepository;
import br.com.leite.aquilla.instagramapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class InstagramApiApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    AwsConfig awsConfig;

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        SpringApplication.run(InstagramApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var user1 = User.builder().email("test1@hotmail.com").gender(GenderEnum.MALE).name("Test 1").phone("5582999999999").username("test1.test").accountType(AccountTypeEnum.STANDARD).build();
        var user2 = User.builder().email("test2@hotmail.com").gender(GenderEnum.MALE).name("Test 2").phone("5582999999999").username("test2.test").accountType(AccountTypeEnum.STANDARD).build();
        var user3 = User.builder().email("test3@hotmail.com").gender(GenderEnum.FEMALE).name("Test 3").phone("5582999999999").username("test3.test").accountType(AccountTypeEnum.STANDARD).build();
        var user4 = User.builder().email("test4@hotmail.com").gender(GenderEnum.MALE).name("Test 4").phone("5582999999999").username("test4.test").accountType(AccountTypeEnum.STANDARD).build();
        var user5 = User.builder().email("test5@hotmail.com").gender(GenderEnum.FEMALE).name("Test 5").phone("5582999999999").username("test5.test").accountType(AccountTypeEnum.STANDARD).build();
        var user6 = User.builder().email("test6@hotmail.com").gender(GenderEnum.FEMALE).name("Test 6").phone("5582999999999").username("test6.test").accountType(AccountTypeEnum.STANDARD).build();
        var user7 = User.builder().email("test7@hotmail.com").gender(GenderEnum.MALE).name("Test 7").phone("5582999999999").username("test7.test").accountType(AccountTypeEnum.STANDARD).build();

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);

        userRepository.saveAll(users);

        user1.getFollowing().add(user2);
        user1.getFollowing().add(user3);
        user1.getFollowing().add(user4);
        user1.getFollowing().add(user7);


        user2.getFollowing().add(user1);
        user2.getFollowing().add(user3);
        user2.getFollowing().add(user5);
        user2.getFollowing().add(user6);

        user7.getFollowing().add(user4);
        user7.getFollowing().add(user6);

        user6.getFollowing().add(user1);

        userRepository.saveAll(users);

        var post1 = Post.builder().author(user1).describe("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur maximus rutrum blandit.").build();
        postRepository.save(post1);

        var file1 = File.builder().name("sum").path("http://localhost:8080/resources/sum.jpg").post(post1).build();
        fileRepository.save(file1);
    }
}
