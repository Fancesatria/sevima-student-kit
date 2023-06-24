<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\PenggunaController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::prefix('pengguna')->group(function(){
    //PENGGUNA AUTH -------------------------
    Route::post('/register', 'App\Http\Controllers\PenggunaController@create')->name('register');
    Route::post('/login', 'App\Http\Controllers\PenggunaController@login')->name('login');
    Route::post('/edit/{id}', 'App\Http\Controllers\PenggunaController@update');
    Route::get('/index', 'App\Http\Controllers\PenggunaController@index');
    Route::get('/show/{id}', 'App\Http\Controllers\PenggunaController@show');
    Route::get('/del-auth/{id}', 'App\Http\Controllers\PenggunaController@destroy');

    });

