package com.hotelops.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;
import com.hotelops.data.local.dao.RoomDao;
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
public final class RoomRepositoryImpl_Factory implements Factory<RoomRepositoryImpl> {
  private final Provider<RoomDao> roomDaoProvider;

  private final Provider<FirebaseFirestore> firestoreProvider;

  public RoomRepositoryImpl_Factory(Provider<RoomDao> roomDaoProvider,
      Provider<FirebaseFirestore> firestoreProvider) {
    this.roomDaoProvider = roomDaoProvider;
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public RoomRepositoryImpl get() {
    return newInstance(roomDaoProvider.get(), firestoreProvider.get());
  }

  public static RoomRepositoryImpl_Factory create(Provider<RoomDao> roomDaoProvider,
      Provider<FirebaseFirestore> firestoreProvider) {
    return new RoomRepositoryImpl_Factory(roomDaoProvider, firestoreProvider);
  }

  public static RoomRepositoryImpl newInstance(RoomDao roomDao, FirebaseFirestore firestore) {
    return new RoomRepositoryImpl(roomDao, firestore);
  }
}
