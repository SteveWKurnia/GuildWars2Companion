package com.example.soa_guildwars2.app.di

import com.example.soa_guildwars2.data.repository.CharacterEntityRepository
import com.example.soa_guildwars2.data.repository.GeneralEntityRepository
import com.example.soa_guildwars2.data.repository.GuildEntityRepository
import com.example.soa_guildwars2.data.repository.HomeEntityRepository
import com.example.soa_guildwars2.data.repository.MiscellaneousEntityRepository
import com.example.soa_guildwars2.domain.CharacterRepository
import com.example.soa_guildwars2.domain.GeneralRepository
import com.example.soa_guildwars2.domain.GuildRepository
import com.example.soa_guildwars2.domain.HomeRepository
import com.example.soa_guildwars2.domain.MiscellaneousRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHomeRepository(homeEntityRepository: HomeEntityRepository): HomeRepository =
        homeEntityRepository

    @Provides
    @Singleton
    fun provideGuildRepository(guildEntityRepository: GuildEntityRepository): GuildRepository =
        guildEntityRepository

    @Provides
    @Singleton
    fun provideMiscellaneousRepository(miscellaneousEntityRepository: MiscellaneousEntityRepository): MiscellaneousRepository =
        miscellaneousEntityRepository

    @Provides
    @Singleton
    fun provideCharacterRepository(characterEntityRepository: CharacterEntityRepository): CharacterRepository =
        characterEntityRepository

    @Provides
    @Singleton
    fun provideGeneralRepository(generalEntityRepository: GeneralEntityRepository): GeneralRepository =
        generalEntityRepository

}