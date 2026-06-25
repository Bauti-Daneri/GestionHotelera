package com.hotelops.data.repository;

import android.content.SharedPreferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hotelops.data.local.dao.HotelDao;
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
public final class AuthRepositoryImpl_Factory implements Factory<AuthRepositoryImpl> {
  private final Provider<FirebaseAuth> firebaseAuthProvider;

  private final Provider<FirebaseFirestore> firestoreProvider;

  private final Provider<UserDao> userDaoProvider;

  private final Provider<HotelDao> hotelDaoProvider;

  private final Provider<SharedPreferences> prefsProvider;

  public AuthRepositoryImpl_Factory(Provider<FirebaseAuth> firebaseAuthProvider,
      Provider<FirebaseFirestore> firestoreProvider, Provider<UserDao> userDaoProvider,
      Provider<HotelDao> hotelDaoProvider, Provider<SharedPreferences> prefsProvider) {
    this.firebaseAuthProvider = firebaseAuthProvider;
    this.firestoreProvider = firestoreProvider;
    this.userDaoProvider = userDaoProvider;
    this.hotelDaoProvider = hotelDaoProvider;
    this.prefsProvider = prefsProvider;
  }

  @Override
  public AuthRepositoryImpl get() {
    return newInstance(firebaseAuthProvider.get(), firestoreProvider.get(), userDaoProvider.get(), hotelDaoProvider.get(), prefsProvider.get());
  }

  public static AuthRepositoryImpl_Factory create(Provider<FirebaseAuth> firebaseAuthProvider,
      Provider<FirebaseFirestore> firestoreProvider, Provider<UserDao> userDaoProvider,
      Provider<HotelDao> hotelDaoProvider, Provider<SharedPreferences> prefsProvider) {
    return new AuthRepositoryImpl_Factory(firebaseAuthProvider, firestoreProvider, userDaoProvider, hotelDaoProvider, prefsProvider);
  }

  public static AuthRepositoryImpl newInstance(FirebaseAuth firebaseAuth,
      FirebaseFirestore firestore, UserDao userDao, HotelDao hotelDao, SharedPreferences prefs) {
    return new AuthRepositoryImpl(firebaseAuth, firestore, userDao, hotelDao, prefs);
  }
}
