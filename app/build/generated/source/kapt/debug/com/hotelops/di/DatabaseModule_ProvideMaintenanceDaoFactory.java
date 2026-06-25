package com.hotelops.di;

import com.hotelops.data.local.dao.MaintenanceDao;
import com.hotelops.data.local.database.HotelOpsDatabase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class DatabaseModule_ProvideMaintenanceDaoFactory implements Factory<MaintenanceDao> {
  private final Provider<HotelOpsDatabase> databaseProvider;

  public DatabaseModule_ProvideMaintenanceDaoFactory(Provider<HotelOpsDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public MaintenanceDao get() {
    return provideMaintenanceDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideMaintenanceDaoFactory create(
      Provider<HotelOpsDatabase> databaseProvider) {
    return new DatabaseModule_ProvideMaintenanceDaoFactory(databaseProvider);
  }

  public static MaintenanceDao provideMaintenanceDao(HotelOpsDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideMaintenanceDao(database));
  }
}
