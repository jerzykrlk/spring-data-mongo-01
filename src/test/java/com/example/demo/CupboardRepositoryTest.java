package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CupboardRepositoryTest {

    @Autowired
    private CupboardRepository cupboardRepository;

    @Test
    public void testFindById() {
        Cupboard cupboard = cupboardRepository.findById("my-cupboard").get();

        assertThat(cupboard.getName()).isEqualTo("my cupboard");
    }

    @Test
    public void testPullExistingCupByObject() {
        cupboardRepository.deleteCupByObject("my-cupboard", "my-cup-1");

        Cupboard cupboard = cupboardRepository.findById("my-cupboard").get();

        assertThat(cupboard.getCups()).extracting("id").doesNotContain("my-cup-1");
    }

    @Test
    public void testPullNewCupByObject() {
        // given
        cupboardRepository.addCup("my-cupboard", new Cup("my-cup-3", "My third cup"));

        Cupboard cupboard = cupboardRepository.findById("my-cupboard").get();
        assertThat(cupboard.getCups()).extracting("id").contains("my-cup-3");

        // when
        cupboardRepository.deleteCupByObject("my-cupboard", "my-cup-3");

        //then
        cupboard = cupboardRepository.findById("my-cupboard").get();
        assertThat(cupboard.getCups()).extracting("id").doesNotContain("my-cup-3");
    }

    @Test
    public void testPullExistingCupByCriteria() {
        cupboardRepository.deleteCupByCriteria("my-cupboard", "my-cup-2");

        Cupboard cupboard = cupboardRepository.findById("my-cupboard").get();

        assertThat(cupboard.getCups()).extracting("id").doesNotContain("my-cup-2");
    }

    @Test
    public void testPullNewCupByCriteria() {
        // given
        cupboardRepository.addCup("my-cupboard", new Cup("my-cup-4", "My third cup"));

        Cupboard cupboard = cupboardRepository.findById("my-cupboard").get();
        assertThat(cupboard.getCups()).extracting("id").contains("my-cup-4");

        // when
        cupboardRepository.deleteCupByCriteria("my-cupboard", "my-cup-4");

        //then
        cupboard = cupboardRepository.findById("my-cupboard").get();
        assertThat(cupboard.getCups()).extracting("id").doesNotContain("my-cup-4");
    }

    //
//    @Test
//    public void testPullExistingByCupCriteria() {
//        Cupboard cupboard = cupboardRepository.findById("my-cupboard").get();
//
//        assertThat(cupboard.getName(), equalTo("my cupboard"));
//    }
//
//    @Test
//    public void testPullAddedByCupCriteria() {
//        Cupboard cupboard = cupboardRepository.findById("my-cupboard").get();
//
//        assertThat(cupboard.getName(), equalTo("my cupboard"));
//    }
}