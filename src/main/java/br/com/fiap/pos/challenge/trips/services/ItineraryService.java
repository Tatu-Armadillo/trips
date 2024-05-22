package br.com.fiap.pos.challenge.trips.services;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.pos.challenge.trips.dto.itinerary.ItineraryDTO;
import br.com.fiap.pos.challenge.trips.dto.itinerary.SimpleListitineraryDTO;
import br.com.fiap.pos.challenge.trips.exception.NotFoundException;
import br.com.fiap.pos.challenge.trips.models.Itinerary;
import br.com.fiap.pos.challenge.trips.repositories.ItineraryRepository;

@Service
public class ItineraryService {

    private final ItineraryRepository itineraryRepository;
    private final CityService cityService;
    private final TravelerService travelerService;

    @Autowired
    public ItineraryService(
            final ItineraryRepository itineraryRepository,
            final CityService cityService,
            final TravelerService travelerService) {
        this.itineraryRepository = itineraryRepository;
        this.cityService = cityService;
        this.travelerService = travelerService;
    }

    public Itinerary createItinerary(final ItineraryDTO dto) {
        final var entity = this.toEntity(dto);
        return this.itineraryRepository.save(entity);
    }

    public Itinerary findById(final Long id) {
        return this.itineraryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Itinerary with id: " + id));
    }

    public Page<SimpleListitineraryDTO> pageItinerariesWithFilter(
            final Pageable pageable,
            final String filter) {
        final var response = this.itineraryRepository.pageItinerariesWithFilter(filter, pageable);
        return response.map(SimpleListitineraryDTO::of);
    }

    private Itinerary toEntity(final ItineraryDTO dto) {
        final var entity = new Itinerary();
        entity.setResume(dto.resume());
        entity.setShared(dto.shared());
        entity.setMoneyQuantity(dto.moneyQuantity());
        entity.setDepartureDate(dto.departureDate());
        entity.setReturnDate(dto.returnDate());
        entity.setCrateDate(LocalDateTime.now());
        entity.setTraveler(this.travelerService.findTravelerByName(dto.travelerName()));
        entity.setCity(this.cityService.findCityByName(dto.nameCity()));
        return entity;
    }

}
