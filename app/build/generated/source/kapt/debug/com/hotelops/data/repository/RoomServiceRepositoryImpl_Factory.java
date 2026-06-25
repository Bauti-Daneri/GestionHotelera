package com.hotelops.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;
import com.hotelops.data.local.dao.RoomServiceDao;
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
public final class RoomServiceRepositoryImpl_Factory implements Factory<RoomServiceRepositoryImpl> {
  private final Provider<RoomServiceDao> roomServiceDaoProvider;

  private final Provider<FirebaseFirestore> firestoreProvider;

  public RoomServiceRepositoryImpl_Factory(Provider<RoomServiceDao> roomServiceDaoProvider,
      Provider<FirebaseFirestore> firestoreProvider) {
    this.roomServiceDaoProvider = roomServiceDaoProvider;
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public RoomServiceRepositoryImpl get() {
    return newInstance(roomServiceDaoProvider.get(), firestoreProvider.get());
  }

  public static RoomServiceRepositoryImpl_Factory create(
      Provider<RoomServiceDao> roomServiceDaoProvider,
      Provider<FirebaseFirestore> firestoreProvider) {
    return new RoomServiceRepositoryImpl_Factory(roomServiceDaoProvider, firestoreProvider);
  }

  public static RoomServiceRepositoryImpl newInstance(RoomServiceDao roomServiceDao,
      FirebaseFirestore firestore) {
    return new RoomServiceRepositoryImpl(roomServiceDao, firestore);
  }
}
