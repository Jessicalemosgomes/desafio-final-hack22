package com.orbitallpayments.cards.controllers;

import com.orbitallpayments.cards.domains.Card;
import com.orbitallpayments.cards.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<Card> save(@RequestBody Card card) {
        Card savedCard = cardService.save(card);
        return new ResponseEntity(savedCard, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updated(@PathVariable String id, @RequestBody Card card) {
        Optional<Card> optional = cardService.findById(Long.valueOf(id));

        // Se não achar registro no banco, devolver 404 NOT_FOUND!
        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Card fetchedCard = optional.get();

        fetchedCard.setCardNumber(card.getCardNumber());
        fetchedCard.setEmbossName(card.getEmbossName());
        fetchedCard.setCustomerName(card.getCustomerName());
        fetchedCard.setDocumentNumber(card.getDocumentNumber());
        fetchedCard.setMotherName(card.getMotherName());
        fetchedCard.setAddress(card.getAddress());
        fetchedCard.setCity(card.getCity());

        Card savedCard = cardService.save(fetchedCard);
        return new ResponseEntity(savedCard, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Card> delete(@PathVariable String id) {
        Optional<Card> optional = cardService.findById(Long.valueOf(id));

        // Se não achar registro no banco, devolver 404 NOT_FOUND!
        if(!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        cardService.deleteById(Long.valueOf(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> findById(@PathVariable String id) {
        Optional<Card> optional = cardService.findById(Long.valueOf(id));
        return optional.map(card -> ResponseEntity.ok().body(card))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Card> findAll() {
        return cardService.findAll();
    }

    @GetMapping("/paginationAndSorting")
    public Page<Card> findAll(Pageable pageable) {
        return cardService.findAll(pageable); // http://localhost:8080/cards/paginationAndSorting?size=5&page=0
    }
}