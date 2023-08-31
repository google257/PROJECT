
package com.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entities.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    @Query("SELECT CASE WHEN COUNT(account) > 0 THEN true ELSE false END FROM Account account WHERE account.id =:accountID")
    boolean doesAccountExistsWithGivenID(@Param("accountID") int accountID);
}
