package com.hotelops.data.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hotelops.data.local.dao.UserDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class UserRepositoryImpl_Factory implements Factory<UserRepositoryImpl> {
  private final Provider<UserDao> userDaoProvider;

  private final Provider<FirebaseAuth> firebaseAuthProvider;

  private final Provider<FirebaseFirestore> firestoreProvider;

  public UserRepositoryImpl_Factory(Provider<UserDao> userDaoProvider,
      Provider<FirebaseAuth> firebaseAuthProvider, Provider<FirebaseFirestore> firestoreProvider) {
    this.userDaoProvider = userDaoProvider;
    this.firebaseAuthProvider = firebaseAuthProvider;
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public UserRepositoryImpl get() {
    return newInstance(userDaoProvider.get(), firebaseAuthProvider.get(), firestoreProvider.get());
  }

  public static UserRepositoryImpl_Factory create(Provider<UserDao> userDaoProvider,
      Provider<FirebaseAuth> firebaseAuthProvider, Provider<FirebaseFirestore> firestoreProvider) {
    return new UserRepositoryImpl_Factory(userDaoProvider, firebaseAuthProvider, firestoreProvider);
  }

  public static UserRepositoryImpl newInstance(UserDao userDao, FirebaseAuth firebaseAuth,
      FirebaseFirestore firestore) {
    return new UserRepositoryImpl(userDao, firebaseAuth, firestore);
  }
}
