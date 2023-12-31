package pl.pjatk.nbp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.pjatk.nbp.NbpResponse;
import pl.pjatk.nbp.Rate;
import pl.pjatk.nbp.model.CurrencyQuery;
import pl.pjatk.nbp.model.CurrencyType;
import pl.pjatk.nbp.repository.CurrencyQueryRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class CurrencyService {
    private final CurrencyQueryRepository currencyQueryRepository;
    private final RestTemplate restTemplate;

    @Autowired

    public CurrencyService(CurrencyQueryRepository currencyQueryRepository, RestTemplate restTemplate) {
        this.currencyQueryRepository = currencyQueryRepository;
        this.restTemplate = restTemplate;
    }

    public List<Rate> getFilteredExchangeRate(String currencyName, LocalDate startDate, LocalDate endDate) {
        // Pobierz dane z API NBP dla danej waluty i liczby dni
        String apiUrl = "http://api.nbp.pl/api/exchangerates/rates/A/{currency}/{startDate}/{endDate}/?format=json";
        String url = apiUrl.replace("{currency}", currencyName)
                .replace("{startDate}", startDate.toString())
                .replace("{endDate}", endDate.toString());
        NbpResponse nbpResponse = restTemplate.getForObject(url, NbpResponse.class);

        // Oblicz średni kurs z pobranych danych
        List<Rate> rates = nbpResponse.getRates();
        double sum = rates.stream().mapToDouble(Rate::getMid).sum();
        double averageRate = sum / rates.size();

        // Zapisz zapytanie do bazy danych
        CurrencyQuery currencyQuery = new CurrencyQuery();
        currencyQuery.setCurrency(CurrencyType.valueOf(currencyName));
        currencyQuery.setStartDate(startDate);
        currencyQuery.setEndDate(endDate);
        currencyQuery.setCalculatedRate(averageRate);
        currencyQuery.setQueryDate(LocalDate.now());
        currencyQuery.setQueryTime(LocalTime.now());
        currencyQuery.setResultCount(nbpResponse.getRates().size());

        currencyQueryRepository.save(currencyQuery);

        return nbpResponse.getRates();
    }
}
