package com.pifrans.multidatabasedynamic.repositories.databases;

import com.pifrans.multidatabasedynamic.entities.databases.Database;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatabaseRepository extends JpaRepository<Database, Long> {
}
