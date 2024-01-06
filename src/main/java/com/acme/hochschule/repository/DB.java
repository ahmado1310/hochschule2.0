/*
 * Copyright (C) 2022 - present Juergen Zimmermann, Hochschule Karlsruhe
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.acme.hochschule.repository;

import com.acme.hochschule.entity.Buero;
import com.acme.hochschule.entity.Dekan;
import com.acme.hochschule.entity.Fakultaet;
import com.acme.hochschule.entity.Professor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Emulation der Datenbasis für persistente Fakultäten, Profs und Büros.
 */
@SuppressWarnings({"UtilityClassCanBeEnum", "UtilityClass", "MagicNumber", "RedundantSuppression"})
final class DB {
    /**
     * Liste der Fakultäten zur Emulation der DB.
     */
    @SuppressWarnings("StaticCollection")
    static final List<Fakultaet> FAKULTAETEN = getFakultaeten();

    private DB() {
    }

    @SuppressWarnings({"FeatureEnvy", "TrailingComment"})
    private static List<Fakultaet> getFakultaeten() {
        return Stream.of(
                Fakultaet.builder()
                    .id(UUID.fromString("30000000-0000-0000-0000-000000000001"))
                    .name("Informatik")
                    .professoren(Stream.of(
                            Professor.builder()
                                .id(UUID.fromString("20000000-0000-0000-0000-000000000001"))
                                .name("Ahmad")
                                .buero(Buero.builder()
                                    .id(UUID.fromString("10000000-0000-0000-0000-000000000001"))
                                    .gebaeude("E")
                                    .build())
                                .build(),
                            Professor.builder()
                                .id(UUID.fromString("20000000-0000-0000-0000-000000000002"))
                                .name("Leon")
                                .buero(Buero.builder()
                                    .id(UUID.fromString("10000000-0000-0000-0000-000000000002"))
                                    .gebaeude("E")
                                    .build())
                                .build())
                        .collect(Collectors.toList()))
                    .dekan(Dekan.builder()
                        .id(UUID.fromString("40000000-0000-0000-0000-000000000001"))
                        .name("Joschua")
                        .build())
                    .build(),
                Fakultaet.builder()
                    .id(UUID.fromString("30000000-0000-0000-0000-000000000002"))
                    .name("Maschinenbau")
                    .professoren(Stream.of(
                            Professor.builder()
                                .id(UUID.fromString("20000000-0000-0000-0000-000000000001"))
                                .name("Ahmad")
                                .buero(Buero.builder()
                                    .id(UUID.fromString("10000000-0000-0000-0000-000000000001"))
                                    .gebaeude("E")
                                    .build())
                                .build(),
                            Professor.builder()
                                .id(UUID.fromString("20000000-0000-0000-0000-000000000004"))
                                .name("Professor Dr. Fischer")
                                .buero(Buero.builder()
                                    .id(UUID.fromString("10000000-0000-0000-0000-000000000004"))
                                    .gebaeude("F")
                                    .build())
                                .build())
                        .collect(Collectors.toList()))
                    .dekan(Dekan.builder()
                        .id(UUID.fromString("40000000-0000-0000-0000-000000000002"))
                        .name("Malik")
                        .build())
                    .build())
            .collect(Collectors.toList());
    }
}
