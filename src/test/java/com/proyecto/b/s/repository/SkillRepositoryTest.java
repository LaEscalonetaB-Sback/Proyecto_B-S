package com.proyecto.b.s.repository;

import com.proyecto.b.s.entity.Skill;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class SkillRepositoryTest {
    @Autowired
    private SkillRepository skillRepository;

    @DisplayName("Test for find by name Skill")
    @Transactional
    @Test
    @DirtiesContext
    void findByName() {
        Skill skill = Skill.builder()
                .id(1L)
                .name("Java")
                .build();
        skillRepository.save(skill);
        Skill skillFind = skillRepository.findByName("Java");

        assertEquals("Java", skillFind.getName());
    }

    @DisplayName("Test for exist Skill")
    @Transactional
    @Test
    @DirtiesContext
    void testExistSkill() {
        Skill skill = Skill.builder()
                .id(1L)
                .name("Java")
                .build();
        Skill savedSkill = skillRepository.save(skill);
        boolean exist = skillRepository.existsById(savedSkill.getId());

        assertTrue(exist);

        boolean notExist = skillRepository.existsById(-1L);

        assertFalse(notExist);
    }

    @DisplayName("Test for update Skill")
    @Transactional
    @Test
    @DirtiesContext
    void checkUpdateSkill() {
        Skill skill = Skill.builder()
                .id(1L)
                .name("Java")
                .build();

        skillRepository.save(skill);

        Optional<Skill> skillOptional = skillRepository.findById(1L);
        assertTrue(skillOptional.isPresent());

        skillOptional.get().setName("Angular");
        skillRepository.save(skillOptional.get());

        Optional<Skill> updatedSkill = skillRepository.findById(1L);
        assertTrue(updatedSkill.isPresent());

        assertEquals("Angular", updatedSkill.get().getName());
    }

    @DisplayName("Test for save Skill")
    @Transactional
    @Test
    @DirtiesContext
    void testSaveSkill() {
        Skill skill = Skill.builder()
                .id(1L)
                .name("Java")
                .build();
        Skill savedSkill = skillRepository.save(skill);

        assertThat(savedSkill).isNotNull();
        assertThat(savedSkill.getId()).isNotNull().isPositive();
    }

    @DisplayName("Test for list Skill empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListSkillsEmpty() {
        List<Skill> skillList = skillRepository.findAll();
        assertTrue(skillList.isEmpty());
    }

    @DisplayName("Test for list Skill not empty")
    @Transactional
    @Test
    @DirtiesContext
    void testListSkillsNotEmpty() {
        Skill skill = Skill.builder()
                .id(1L)
                .name("Java")
                .build();
        skillRepository.save(skill);

        List<Skill> skills = skillRepository.findAll();
        assertFalse(skills.isEmpty());
    }

    @DisplayName("Test for delete Skill")
    @Transactional
    @Test
    @DirtiesContext
    void testDeleteSkill() {
        Skill skill = Skill.builder()
                .id(1L)
                .name("Java")
                .build();

        Skill save = skillRepository.save(skill);
        assertNotNull(save);

        skillRepository.delete(save);

        Optional<Skill> deletedSkill = skillRepository.findById(1L);
        assertFalse(deletedSkill.isPresent());
    }
}