package com.hotelops.domain.usecase.maintenance;

import com.hotelops.domain.repository.MaintenanceRepository;
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
public final class UpdateTicketStatusUseCase_Factory implements Factory<UpdateTicketStatusUseCase> {
  private final Provider<MaintenanceRepository> maintenanceRepositoryProvider;

  public UpdateTicketStatusUseCase_Factory(
      Provider<MaintenanceRepository> maintenanceRepositoryProvider) {
    this.maintenanceRepositoryProvider = maintenanceRepositoryProvider;
  }

  @Override
  public UpdateTicketStatusUseCase get() {
    return newInstance(maintenanceRepositoryProvider.get());
  }

  public static UpdateTicketStatusUseCase_Factory create(
      Provider<MaintenanceRepository> maintenanceRepositoryProvider) {
    return new UpdateTicketStatusUseCase_Factory(maintenanceRepositoryProvider);
  }

  public static UpdateTicketStatusUseCase newInstance(MaintenanceRepository maintenanceRepository) {
    return new UpdateTicketStatusUseCase(maintenanceRepository);
  }
}
