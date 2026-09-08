package com.example.CoffeeBackend.repository;

import com.example.CoffeeBackend.entity.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

    List<Coffee> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrCategoryContainingIgnoreCase(
            String name,
            String description,
            String category
    );

    @Modifying
    @Query(
            value = """
                    UPDATE coffee
                    SET embedding = CAST(:embedding AS vector)
                    WHERE id = :id
                    """,
            nativeQuery = true
    )
    void updateEmbedding(
            @Param("id") Long id,
            @Param("embedding") String embedding
    );
    @Query(
            value = """
                SELECT *
                FROM coffee
                WHERE embedding IS NULL
                """,
            nativeQuery = true
    )
    List<Coffee> findCoffeesWithoutEmbedding();
    @Query(value = """
        SELECT *
        FROM coffee
        WHERE embedding IS NOT NULL
        ORDER BY embedding <=> CAST(:embedding AS vector)
        LIMIT 3
        """, nativeQuery = true)
    List<Coffee> findSimilarCoffees(
            @Param("embedding") String embedding
    );
}