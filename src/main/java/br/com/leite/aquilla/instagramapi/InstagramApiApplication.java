package br.com.leite.aquilla.instagramapi;

import br.com.leite.aquilla.instagramapi.entity.Comment;
import br.com.leite.aquilla.instagramapi.entity.File;
import br.com.leite.aquilla.instagramapi.entity.Post;
import br.com.leite.aquilla.instagramapi.entity.User;
import br.com.leite.aquilla.instagramapi.enums.AccountTypeEnum;
import br.com.leite.aquilla.instagramapi.enums.GenderEnum;
import br.com.leite.aquilla.instagramapi.repository.CommentRepository;
import br.com.leite.aquilla.instagramapi.repository.FileRepository;
import br.com.leite.aquilla.instagramapi.repository.PostRepository;
import br.com.leite.aquilla.instagramapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;

@SpringBootApplication
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class InstagramApiApplication implements CommandLineRunner {

    UserRepository userRepository;
    PostRepository postRepository;
    FileRepository fileRepository;
    CommentRepository commentRepository;

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        SpringApplication.run(InstagramApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var users = new ArrayList<User>();
        for (var i = 0; i < 50; i++) {
            var user = User.builder()
                    .email("test" + (i + 1) + "@hotmail.com")
                    .name("Test " + (i + 1))
                    .phone("9999999999999")
                    .username("test" + (i + 1) + ".test")
                    .gender(GenderEnum.MALE)
                    .accountType(AccountTypeEnum.STANDARD)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
            users.add(user);
        }
        userRepository.saveAll(users);

        users.get(0).getFollowing().add(users.get(1));
        users.get(0).getFollowing().add(users.get(2));
        users.get(0).getFollowing().add(users.get(3));
        users.get(0).getFollowing().add(users.get(6));

        users.get(1).getFollowing().add(users.get(0));
        users.get(1).getFollowing().add(users.get(2));
        users.get(1).getFollowing().add(users.get(4));
        users.get(1).getFollowing().add(users.get(5));

        users.get(6).getFollowing().add(users.get(3));
        users.get(6).getFollowing().add(users.get(5));

        users.get(5).getFollowing().add(users.get(0));

        userRepository.saveAll(users);

        var post = Post.builder()
                .text("post post post post post")
                .createdAt(LocalDateTime.now())
                .user(users.get(0))
                .build();
        postRepository.save(post);

        var file = File.builder()
                .name("xxx.xxx")
                .path("http://xxx:8080/xxx.xxx")
                .createdAt(LocalDateTime.now())
                .post(post)
                .build();
        fileRepository.save(file);

        var comments = new ArrayList<Comment>();
        for (var i = 0; i < 50; i++) {
            var comment = Comment.builder()
                    .text("comment " + i)
                    .createdAt(LocalDateTime.now())
                    .user(users.get((int) (Math.random() * ((49) + 1))))
                    .post(post)
                    .build();
            comments.add(comment);
        }
        commentRepository.saveAll(comments);
    }
}
