package ru.gilko.gatewayimpl.controller_impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import ru.gilko.gatewayapi.controller.GatewayController;
import ru.gilko.gatewayapi.dto.CarBookDto;
import ru.gilko.gatewayapi.dto.car.CarDto;
import ru.gilko.gatewayapi.dto.rental.RentalCreationOutDto;
import ru.gilko.gatewayapi.dto.rental.RentalDto;
import ru.gilko.gatewayapi.dto.wrapper.PageableCollectionOutDto;
import ru.gilko.gatewayimpl.service.api.GatewayService;

import java.util.List;
import java.util.UUID;


@RestController
@Slf4j
public class GatewayControllerImpl implements GatewayController {
    private final GatewayService gatewayService;

    public GatewayControllerImpl(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @Override
    public ResponseEntity<String> authorize() {
        return null;
    }

    @Override
    public ResponseEntity<String> callback() {
        return null;
    }

    @Override
    public PageableCollectionOutDto<CarDto> getAllCars(boolean showAll, int page, int size) {
        log.info("Request for reading cars. Request params: showAll {}, page {}, size {}", showAll, page, showAll);

        return gatewayService.getAllCars(showAll, page, size);
    }

    @Override
    public ResponseEntity<List<RentalDto>> getRental(Authentication authentication) {
        log.info("Request for reading all rental info of user {}", authentication.getName());

        return ResponseEntity.ok(gatewayService.getRental(authentication.getName()));
    }

    @Override
    public ResponseEntity<RentalDto> getRental(Authentication authentication, UUID rentalUid) {
        log.info("Request for reading user's {} rental {}", authentication.getName(), rentalUid);

        return ResponseEntity.ok(gatewayService.getRental(authentication.getName(), rentalUid));
    }

    @Override
    public ResponseEntity<RentalCreationOutDto> bookCar(Authentication authentication, CarBookDto carBookDto) {
        log.info("Request for booking car {} by user {}", carBookDto, authentication.getName());

        RentalCreationOutDto rentalCreationOutDto = gatewayService.bookCar(authentication.getName(), carBookDto);

        return ResponseEntity.ok(rentalCreationOutDto);
    }

    @Override
    public ResponseEntity<?> finishRental(Authentication authentication, UUID rentalUid) {
        log.info("Request for finishing rental {} of user {}", rentalUid, authentication.getName());

        gatewayService.finishRental(authentication.getName(), rentalUid);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> cancelRental(Authentication authentication, UUID rentalUid) {
        log.info("Request for canceling rental {} of user {}", rentalUid, authentication.getName());

        gatewayService.cancelRental(authentication.getName(), rentalUid);

        return ResponseEntity.noContent().build();
    }
}
