package ru.gilko.gatewayapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.gilko.gatewayapi.constants.ControllerUrls;
import ru.gilko.gatewayapi.dto.CarBookDto;
import ru.gilko.gatewayapi.dto.car.CarDto;
import ru.gilko.gatewayapi.dto.rental.RentalCreationOutDto;
import ru.gilko.gatewayapi.dto.rental.RentalDto;
import ru.gilko.gatewayapi.dto.wrapper.PageableCollectionOutDto;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface GatewayController {
    @GetMapping(ControllerUrls.AUTH)
    ResponseEntity<String> authorize();

    @GetMapping(ControllerUrls.CALLBACK)
    ResponseEntity<String> callback();

    @GetMapping(ControllerUrls.CARS_URL)
    PageableCollectionOutDto<CarDto> getAllCars(@RequestParam boolean showAll,
                                                @RequestParam int page,
                                                @RequestParam int size);

    @GetMapping(ControllerUrls.RENTAL_URL)
    ResponseEntity<List<RentalDto>> getRental(Authentication authentication);

    @GetMapping(ControllerUrls.RENTAL_URL_WITH_ID)
    ResponseEntity<RentalDto> getRental(Authentication authentication,
                                        @PathVariable UUID rentalUid);

    @PostMapping(ControllerUrls.RENTAL_URL)
    ResponseEntity<RentalCreationOutDto> bookCar(Authentication authentication,
                                                 @RequestBody @Valid CarBookDto carBookDto);

    @PostMapping(ControllerUrls.RENTAL_URL_FINISH)
    ResponseEntity<?> finishRental(Authentication authentication,
                                   @PathVariable UUID rentalUid);

    @DeleteMapping(ControllerUrls.RENTAL_URL_WITH_ID)
    ResponseEntity<?> cancelRental(Authentication authentication,
                                   @PathVariable UUID rentalUid);

}
