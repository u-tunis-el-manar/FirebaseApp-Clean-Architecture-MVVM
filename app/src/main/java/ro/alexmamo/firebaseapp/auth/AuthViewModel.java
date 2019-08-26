package ro.alexmamo.firebaseapp.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.AuthCredential;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {
    private AuthRepository authRepository;
    LiveData<User> authenticatedUserLiveData = new MutableLiveData<>();
    LiveData<User> createdUserLiveData = new MutableLiveData<>();

    @Inject
    AuthViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    void signInWithGoogle(AuthCredential googleAuthCredential) {
        authenticatedUserLiveData = authRepository.firebaseSignInWithGoogle(googleAuthCredential);
    }

    void createUser(User authenticatedUser) {
        createdUserLiveData = authRepository.createUserInFirestoreIfNotExists(authenticatedUser);
    }
}