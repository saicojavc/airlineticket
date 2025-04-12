package com.saico.airlineticket.ui.navigation

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class NavigationModule {
    @Provides
    @Singleton
    fun providesNavigator(): Navigator = ComposeNavigator()
}