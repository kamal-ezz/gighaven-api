package com.kamal.gighaven.repositories;

import com.kamal.gighaven.entities.Profile;
import com.kamal.gighaven.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
