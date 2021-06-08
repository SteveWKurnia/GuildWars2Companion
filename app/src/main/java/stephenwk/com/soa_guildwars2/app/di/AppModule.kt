package stephenwk.com.soa_guildwars2.app.di

import com.example.soa_guildwars2.data.repository.*
import com.example.soa_guildwars2.domain.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHomeRepository(homeEntityRepository: HomeEntityRepository): HomeRepository = homeEntityRepository

    @Provides
    @Singleton
    fun provideGuildRepository(guildEntityRepository: GuildEntityRepository): GuildRepository = guildEntityRepository

    @Provides
    @Singleton
    fun provideMiscellaneousRepository(miscellaneousEntityRepository: MiscellaneousEntityRepository): MiscellaneousRepository = miscellaneousEntityRepository

    @Provides
    @Singleton
    fun provideCharacterRepository(characterEntityRepository: CharacterEntityRepository): CharacterRepository = characterEntityRepository

    @Provides
    @Singleton
    fun provideGeneralRepository(generalEntityRepository: GeneralEntityRepository): GeneralRepository = generalEntityRepository

}