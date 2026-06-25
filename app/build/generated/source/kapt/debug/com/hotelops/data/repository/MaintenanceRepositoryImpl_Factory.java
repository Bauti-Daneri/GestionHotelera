package com.hotelops.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;
import com.hotelops.data.local.dao.MaintenanceDao;
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
public final class MaintenanceRepositoryImpl_Factory implements Factory<MaintenanceRepositoryImpl> {
  private final Provider<MaintenanceDao> maintenanceDaoProvider;

  private final Provider<FirebaseFirestore> firestoreProvider;

  public MaintenanceRepositoryImpl_Factory(Provider<MaintenanceDao> maintenanceDaoProvider,
      Provider<FirebaseFirestore> firestoreProvider) {
    this.maintenanceDaoProvider = maintenanceDaoProvider;
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public MaintenanceRepositoryImpl get() {
    return newInstance(maintenanceDaoProvider.get(), firestoreProvider.get());
  }

  public static MaintenanceRepositoryImpl_Factory create(
      Provider<MaintenanceDao> maintenanceDaoProvider,
      Provider<FirebaseFirestore> firestoreProvider) {
    return new MaintenanceRepositoryImpl_Factory(maintenanceDaoProvider, firestoreProvider);
  }

  public static MaintenanceRepositoryImpl newInstance(MaintenanceDao maintenanceDao,
      FirebaseFirestore firestore) {
    return new MaintenanceRepositoryImpl(maintenanceDao, firestore);
  }
}
