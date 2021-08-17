package a.gleb.reactiverest.service;

import a.gleb.reactiverest.models.Account;
import a.gleb.reactiverest.models.PostModel;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

@Log4j
@Component
public class CheckingBodyService {

    public boolean checkPostModelFromRequest(PostModel post){
        return post.getTitle() != null && post.getDescription() != null && post.getFull_story() != null
                && post.getAuthorDTO() != null;
    }

    public boolean checkAccountModelFromRequest(Account account){
        return account.getUsername() != null && account.getPassword() != null && account.getName() != null &&
                account.getSurname() != null && account.getEmail() != null;
    }


}
