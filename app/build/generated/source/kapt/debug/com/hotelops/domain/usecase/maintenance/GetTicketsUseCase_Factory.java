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
public final class GetTicketsUseCase_Factory implements Factory<GetTicketsUseCase> {
  private final Provider<MaintenanceRepository> maintenanceRepositoryProvider;

  public GetTicketsUseCase_Factory(Provider<MaintenanceRepository> maintenanceRepositoryProvider) {
    this.maintenanceRepositoryProvider = maintenanceRepositoryProvider;
  }

  @Override
  public GetTicketsUseCase get() {
    return newInstance(maintenanceRepositoryProvider.get());
  }

  public static GetTicketsUseCase_Factory create(
      Provider<MaintenanceRepository> maintenanceRepositoryProvider) {
    return new GetTicketsUseCase_Factory(maintenanceRepositoryProvider);
  }

  public static GetTicketsUseCase newInstance(MaintenanceRepository maintenanceRepository) {
    return new GetTicketsUseCase(maintenanceRepository);
  }
}
