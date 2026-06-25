package com.hotelops.presentation.maintenance;

import com.hotelops.domain.usecase.maintenance.CreateTicketUseCase;
import com.hotelops.domain.usecase.maintenance.GetTicketsUseCase;
import com.hotelops.domain.usecase.maintenance.UpdateTicketStatusUseCase;
import com.hotelops.domain.usecase.room.GetRoomsUseCase;
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
public final class MaintenanceViewModel_Factory implements Factory<MaintenanceViewModel> {
  private final Provider<GetTicketsUseCase> getTicketsUseCaseProvider;

  private final Provider<GetRoomsUseCase> getRoomsUseCaseProvider;

  private final Provider<CreateTicketUseCase> createTicketUseCaseProvider;

  private final Provider<UpdateTicketStatusUseCase> updateTicketStatusUseCaseProvider;

  public MaintenanceViewModel_Factory(Provider<GetTicketsUseCase> getTicketsUseCaseProvider,
      Provider<GetRoomsUseCase> getRoomsUseCaseProvider,
      Provider<CreateTicketUseCase> createTicketUseCaseProvider,
      Provider<UpdateTicketStatusUseCase> updateTicketStatusUseCaseProvider) {
    this.getTicketsUseCaseProvider = getTicketsUseCaseProvider;
    this.getRoomsUseCaseProvider = getRoomsUseCaseProvider;
    this.createTicketUseCaseProvider = createTicketUseCaseProvider;
    this.updateTicketStatusUseCaseProvider = updateTicketStatusUseCaseProvider;
  }

  @Override
  public MaintenanceViewModel get() {
    return newInstance(getTicketsUseCaseProvider.get(), getRoomsUseCaseProvider.get(), createTicketUseCaseProvider.get(), updateTicketStatusUseCaseProvider.get());
  }

  public static MaintenanceViewModel_Factory create(
      Provider<GetTicketsUseCase> getTicketsUseCaseProvider,
      Provider<GetRoomsUseCase> getRoomsUseCaseProvider,
      Provider<CreateTicketUseCase> createTicketUseCaseProvider,
      Provider<UpdateTicketStatusUseCase> updateTicketStatusUseCaseProvider) {
    return new MaintenanceViewModel_Factory(getTicketsUseCaseProvider, getRoomsUseCaseProvider, createTicketUseCaseProvider, updateTicketStatusUseCaseProvider);
  }

  public static MaintenanceViewModel newInstance(GetTicketsUseCase getTicketsUseCase,
      GetRoomsUseCase getRoomsUseCase, CreateTicketUseCase createTicketUseCase,
      UpdateTicketStatusUseCase updateTicketStatusUseCase) {
    return new MaintenanceViewModel(getTicketsUseCase, getRoomsUseCase, createTicketUseCase, updateTicketStatusUseCase);
  }
}
