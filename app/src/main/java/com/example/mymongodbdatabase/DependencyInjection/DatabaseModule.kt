package com.example.mymongodbdatabase.DependencyInjection

import com.example.mymongodbdatabase.data.MongoRepository
import com.example.mymongodbdatabase.data.MongoRepositoryImpl
import com.example.mymongodbdatabase.model.Address
import com.example.mymongodbdatabase.model.Person
import com.example.mymongodbdatabase.model.Pet
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule
{

    @Singleton
    @Provides
    fun provideRealm(): Realm {


    val config = RealmConfiguration.Builder(
        schema = setOf(
             Address::class, Person::class, Pet::class
        )).compactOnLaunch().build()

        return Realm.open(config)

    }

    @Singleton
    @Provides
    fun provideMongoRepository(realm: Realm):MongoRepositoryImpl  // different in yt
    {
        return MongoRepositoryImpl(realm = realm)
    }

}