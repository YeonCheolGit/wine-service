package com.example.winesearchservice.repository;

import com.example.winesearchservice.entity.Wine;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepository extends CrudRepository<Wine, Long> {

    @Query(value = "insert into wine_service.wine(wineName) value (:nameList)",nativeQuery = true)
    void saveNameList(@Param("nameList") String nameList);

    @Query(value = "insert into wine_service.wine(price) value (:priceList)",nativeQuery = true)
    void savePriceList(@Param("priceList") Integer priceList);

    @Modifying
    @Query(value = "insert into wine_service.wine(winename, price) values (:nameList, :priceList)", nativeQuery = true)
    void saveWineNameAndPrice(@Param("nameList") String nameList,
                              @Param("priceList") Integer priceList);
}
