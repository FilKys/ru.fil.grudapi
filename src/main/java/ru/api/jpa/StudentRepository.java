package ru.api.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.api.entity.Student;

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
}
