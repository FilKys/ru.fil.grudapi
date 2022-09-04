package ru.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.api.modles.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE public.student SET  name= :name , passport= :passport WHERE id = :id",
            nativeQuery = true
    )
    void update(@Param("id") long id,
                   @Param("name") String name,
                   @Param("passport") String passport);


    @Query(value = "SELECT * FROM student limit :limit offset :offset",nativeQuery = true)
    List<Student> findAll(@Param("limit") Integer limit, @Param("offset") Integer offset);

    boolean existsByPassport(String passport);
}
