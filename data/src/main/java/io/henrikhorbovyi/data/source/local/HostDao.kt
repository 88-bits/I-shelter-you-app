package io.henrikhorbovyi.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.henrikhorbovyi.data.source.local.entity.Host
import kotlinx.coroutines.flow.Flow

@Dao
interface HostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeHosts(vararg host: Host)

    @Query("select * from host")
    fun fetchAllHosts(): Flow<List<Host>>
}