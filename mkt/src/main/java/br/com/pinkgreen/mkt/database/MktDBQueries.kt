package br.com.pinkgreen.mkt.database

import android.content.ContentValues
import br.com.pinkgreen.mkt.ui.vo.MktProductResponseVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class MktDBQueries(private val dbHelper: MktDBHelper) {
    fun getCart(): Flow<List<MktProductResponseVO>> {
        val cursor = dbHelper.readableDatabase.query(
            MktDBSchema.Product.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        val items = mutableListOf<MktProductResponseVO>()
        with(cursor) {
            while (moveToNext()) {
                val dbItemId = getInt(getColumnIndexOrThrow(MktDBSchema.Product.COLUMN_NAME_ID))
                val dbItemName =
                    getString(getColumnIndexOrThrow(MktDBSchema.Product.COLUMN_NAME_NAME))
                val dbItemImage =
                    getString(getColumnIndexOrThrow(MktDBSchema.Product.COLUMN_NAME_IMAGE))
                val dbItemPrice =
                    getDouble(getColumnIndexOrThrow(MktDBSchema.Product.COLUMN_NAME_PRICE))

                val productResponseVO = MktProductResponseVO(
                    id = dbItemId,
                    name = dbItemName,
                    mainImage = dbItemImage,
                    price = dbItemPrice,
                    active = null,
                    categories = null,
                    brand = null
                )
                items.add(productResponseVO)
            }
        }
        cursor.close()
        return flowOf(items)
    }

    fun deleteProduct(id: Int) {
        val selection = "${MktDBSchema.Product.COLUMN_NAME_ID} LIKE ?"
        val selectionArgs = arrayOf(id.toString())
        val deletedRows = dbHelper.writableDatabase.delete(
            MktDBSchema.Product.TABLE_NAME,
            selection,
            selectionArgs
        )
    }

    fun addProduct(productResponseVO: MktProductResponseVO): Long {
        val values = ContentValues().apply {
            put(MktDBSchema.Product.COLUMN_NAME_ID, productResponseVO.id)
            put(MktDBSchema.Product.COLUMN_NAME_NAME, productResponseVO.name)
            put(MktDBSchema.Product.COLUMN_NAME_IMAGE, productResponseVO.mainImage)
            put(MktDBSchema.Product.COLUMN_NAME_PRICE, productResponseVO.price)
        }

        return dbHelper.writableDatabase.insert(MktDBSchema.Product.TABLE_NAME, null, values)
    }

    fun clearCart() {
        val deletedRows =
            dbHelper.writableDatabase.delete(MktDBSchema.Product.TABLE_NAME, null, null)
    }

    fun destroy() {
        dbHelper.close()
    }
}