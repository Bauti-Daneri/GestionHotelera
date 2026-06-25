package com.hotelops.di;

import com.hotelops.data.local.dao.HotelDao;
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
public final class DatabaseModule_ProvideHotelDaoFactory implements Factory<HotelDao> {
  private final Provider<HotelOpsDatabase> databaseProvider;

  public DatabaseModule_ProvideHotelDaoFactory(Provider<HotelOpsDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public HotelDao get() {
    return provideHotelDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideHotelDaoFactory create(
      Provider<HotelOpsDatabase> databaseProvider) {
    return new DatabaseModule_ProvideHotelDaoFactory(databaseProvider);
  }

  public static HotelDao provideHotelDao(HotelOpsDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideHotelDao(database));
  }
}
