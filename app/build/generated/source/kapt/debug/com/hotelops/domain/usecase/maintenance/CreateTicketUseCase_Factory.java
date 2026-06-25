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
public final class CreateTicketUseCase_Factory implements Factory<CreateTicketUseCase> {
  private final Provider<MaintenanceRepository> maintenanceRepositoryProvider;

  public CreateTicketUseCase_Factory(
      Provider<MaintenanceRepository> maintenanceRepositoryProvider) {
    this.maintenanceRepositoryProvider = maintenanceRepositoryProvider;
  }

  @Override
  public CreateTicketUseCase get() {
    return newInstance(maintenanceRepositoryProvider.get());
  }

  public static CreateTicketUseCase_Factory create(
      Provider<MaintenanceRepository> maintenanceRepositoryProvider) {
    return new CreateTicketUseCase_Factory(maintenanceRepositoryProvider);
  }

  public static CreateTicketUseCase newInstance(MaintenanceRepository maintenanceRepository) {
    return new CreateTicketUseCase(maintenanceRepository);
  }
}
