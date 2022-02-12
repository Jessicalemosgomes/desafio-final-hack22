package com.orbitallpayments.cards.repositories;

import com.orbitallpayments.cards.domains.Card;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends PagingAndSortingRepository<Card, Long> {

}