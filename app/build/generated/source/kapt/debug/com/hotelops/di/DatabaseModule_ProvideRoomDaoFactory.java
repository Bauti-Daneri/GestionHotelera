package com.hotelops.di;

import com.hotelops.data.local.dao.RoomDao;
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
public final class DatabaseModule_ProvideRoomDaoFactory implements Factory<RoomDao> {
  private final Provider<HotelOpsDatabase> databaseProvider;

  public DatabaseModule_ProvideRoomDaoFactory(Provider<HotelOpsDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public RoomDao get() {
    return provideRoomDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideRoomDaoFactory create(
      Provider<HotelOpsDatabase> databaseProvider) {
    return new DatabaseModule_ProvideRoomDaoFactory(databaseProvider);
  }

  public static RoomDao provideRoomDao(HotelOpsDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideRoomDao(database));
  }
}
