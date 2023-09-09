package pl.pjatk.nbp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.nbp.CurrencyRequest;
import pl.pjatk.nbp.Rate;
import pl.pjatk.nbp.model.CurrencyType;
import pl.pjatk.nbp.service.CurrencyService;

import java.time.LocalDate;
import java.util.List;

@RestController
@Tag(name = "Kontroler Walutowy", description = "Endpointy do zarządzania walutami")
public class CurrencyController {

    private final CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping("/exchange-rate")
    @Operation(summary = "Średni kurs walutowy", description = "Pobierz średni kurs walutowy z określonego okresu")
    public ResponseEntity<List<Rate>> getFilteredExchangeRate(@RequestBody CurrencyRequest request) {
        List<Rate> filteredRates = currencyService.getFilteredExchangeRate(request.getCurrencyName(), request.getStartDate(), request.getEndDate());
        return ResponseEntity.ok(filteredRates);
    }
}
