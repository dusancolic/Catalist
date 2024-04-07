package com.example.catalist.repository

import com.example.catalist.api.model.CatData
import com.example.catalist.api.model.Image
import com.example.catalist.api.model.Weight

val SampleData = listOf(
    CatData(
        id = "1",
        name = "Abyssinian",
        alt_names = "Aby",
        description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        origin = "Ethiopia, Somalia, Srbistan, Ethiopia, Somalia, Srbistan",
        temperament = "Active, Energetic, Independent, Intelligent, Gentle",
        life_span = "12 - 15",
        weight = Weight(
            imperial = "7 - 10",
            metric = "3 - 5"),
        adaptability = 5,
        grooming = 2,
        stranger_friendly = 3,
        dog_friendly = 4,
        energy_level = 1,
        rare = 0,
        wikipedia_url = "https://www.petfinder.com/cat-breeds/abyssinian/",
        image = Image(
            id = "1",
            url = "https://cdn2.thecatapi.com/images/MTUwMTk3NQ.jpg",
            width = 500,
            height = 500,
        ),
    ),
    CatData(
        id = "2",
        name = "American Bobtail",
        alt_names = "Bobtail",
        description = "American Bobtails are loving and incredibly intelligent cats possessing a distinctive wild appearance.",
        origin = "United States, North America",
        temperament = "Intelligent, Interactive, Lively, Playful, Sensitive",
        life_span = "11 - 15",
        weight = Weight(
            imperial = "7 - 10",
            metric = "3 - 5"),
        adaptability = 5,
        grooming = 5,
        stranger_friendly = 4,
        dog_friendly = 5,
        energy_level = 5,
        rare = 0,
        wikipedia_url = "https://www.petfinder.com/cat-breeds/american-bobtail/",
        image = Image(
            id = "2",
            url = "https://cdn2.thecatapi.com/images/MTUwMTk3NQ.jpg",
            width = 500,
            height = 500,
        ),
    ),
    CatData(
        id = "3",
        name = "American Curl",
        alt_names ="",
        description = "The American Curl is best known for its unique ears that curl backward, creating a cute and unusual look.",
        origin = "United States, Serbia, Spain",
        temperament = "Affectionate, Curious, Intelligent, Interactive, Lively",
        life_span = "12 - 16",
        weight = Weight(
            imperial = "7 - 10",
            metric = "3 - 5"),
        adaptability = 5,
        grooming = 5,
        stranger_friendly = 4,
        dog_friendly = 5,
        energy_level = 3,
        rare = 0,
        wikipedia_url = "https://www.petfinder.com/cat-breeds/american-curl/",
        image = Image(
            id = "3",
            url = "https://cdn2.thecatapi.com/images/MTUwMTk3NQ.jpg",
            width = 500,
            height = 500,
        ),
    ),
    CatData(
        id = "4",
        name = "American Shorthair",
        alt_names = "Domestic Shorthair",
        description = "The American Shorthair is a breed of domestic cat believed to be descended from European cats brought to North America by early settlers to protect valuable cargo from mice and rats.sdadsadsdasdsadahjsgd jasgd jgashjdg ahjsgd hgasg djhagshjdgajhs gdhags jdgajsgd jagdjags sdgaj gdasjg djags djagshj dgjahgd ashdg jagsd jasgd ja",
        origin = "United States",
        temperament = "Active, Curious, Easy Going, Playful, Calm",
        life_span = "15 - 17",
        weight = Weight(
            imperial = "7 - 10",
            metric = "3 - 5"),
        adaptability = 5,
        grooming = 5,
        stranger_friendly = 5,
        dog_friendly = 5,
        energy_level = 3,
        rare = 0,
        wikipedia_url = "https://www.petfinder.com/cat-breeds/american-shorthair/",
        image = Image(
            id = "4",
            url = "https://cdn2.thecatapi.com/images/MTUwMTk3NQ.jpg",
            width = 500,
            height = 500,
        ),
    ),

)