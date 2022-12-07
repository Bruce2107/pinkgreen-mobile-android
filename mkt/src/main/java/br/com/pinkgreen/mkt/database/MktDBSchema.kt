package br.com.pinkgreen.mkt.database

import android.provider.BaseColumns

internal object MktDBSchema {
    object Product : BaseColumns {
        const val TABLE_NAME = "product"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_PRICE = "price"
        const val COLUMN_NAME_IMAGE = "image"
    }
    const val SQL_CREATE_ENTRIES =
        "CREATE TABLE ${Product.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${Product.COLUMN_NAME_ID} INTEGER," +
                "${Product.COLUMN_NAME_NAME} TEXT," +
                "${Product.COLUMN_NAME_IMAGE} TEXT," +
                "${Product.COLUMN_NAME_PRICE} DOUBLE)"

    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${Product.TABLE_NAME}"

}