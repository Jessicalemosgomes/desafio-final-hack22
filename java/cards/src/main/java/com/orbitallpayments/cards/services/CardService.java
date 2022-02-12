package com.orbitallpayments.cards.services;

import com.orbitallpayments.cards.domains.Card;
import com.orbitallpayments.cards.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    public Optional<Card> findById(Long id) {
        return cardRepository.findById(id);
    }

    public Page<Card> findAll(Pageable pageable) {
        return cardRepository.findAll(pageable);
    }

    public List<Card> findAll() {
        return (List<Card>) cardRepository.findAll();
    }

    public Card save(Card card) {
        return cardRepository.save(card);
    }

    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }
}