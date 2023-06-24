<?php

namespace App\Http\Controllers;

use App\Models\Note;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use App\Http\Requests\StoreNoteRequest;
use App\Http\Requests\UpdateNoteRequest;

class NoteController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index($id)
    {
        $data = DB::table('notes')
        ->join('penggunas','penggunas.id','=','notes.idpengguna')
        ->select('notes.*', 'notes.description','penggunas.id','penggunas.pengguna')
        ->where('penggunas.id','=', $id)
        ->orderBy('notes.created_at','desc')
        ->get();


        return response()->json($data);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create(Request $request)
    {
        $this->validate($request, [
            'title'=>'string',
            'description'=>'string'
        ]);

        $data = [
            'title'=>$request->input('title'),
            'description'=>$request->input('description'),
            'idpengguna'=>$request->input('idpengguna')
        ];

        $run = Note::create($data);

        if($run){
            return response()->json([
                'status'=>200,
                'data'=>$data
            ]);
        } else {
            return response()->json([
                'status'=>400,
                'data'=>''
            ]);
        }
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \App\Http\Requests\StoreNoteRequest  $request
     * @return \Illuminate\Http\Response
     */
    public function store(StoreNoteRequest $request)
    {
        //
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Note  $note
     * @return \Illuminate\Http\Response
     */
    public function show(Note $note)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  \App\Models\Note  $note
     * @return \Illuminate\Http\Response
     */
    public function edit(Note $note)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \App\Http\Requests\UpdateNoteRequest  $request
     * @param  \App\Models\Note  $note
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $this->validate($request, [
            'title'=>'string',
            'description'=>'string'
        ]);

        $data = [
            'title'=>$request->input('title'),
            'description'=>$request->input('description'),
            'idpengguna'=>$request->input('idpengguna')
        ];

        $run = Note::where('id', $id)->update($data);

        if($run){
            return response()->json([
                'status'=>200,
                'data'=>$data
            ]);
        } else {
            return response()->json([
                'status'=>400,
                'data'=>''
            ]);
        }
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  \App\Models\Note  $note
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $run = Note::where('id',$id)->delete();

        if($run){
            return response()->json([
                'pesan'=>'Data berhasil dihapus',
                'status'=>200
            ]);
        }
    }
}
