package com.example.soa_guildwars2.data.mapper

import com.example.soa_guildwars2.data.models.AccountEntityData
import com.example.soa_guildwars2.domain.datamodel.AccountDataModel
import javax.inject.Inject

class AccountDataMapper @Inject constructor() {

    fun map(accountEntityData: AccountEntityData):
            AccountDataModel = AccountDataModel(
                id = accountEntityData.id,
                name = accountEntityData.name,
                age = accountEntityData.age,
                guilds = accountEntityData.guilds
        )

}