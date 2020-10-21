import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IProfileMySuffix, defaultValue } from 'app/shared/model/profile-my-suffix.model';

export const ACTION_TYPES = {
  FETCH_PROFILE_LIST: 'profile/FETCH_PROFILE_LIST',
  FETCH_PROFILE: 'profile/FETCH_PROFILE',
  CREATE_PROFILE: 'profile/CREATE_PROFILE',
  UPDATE_PROFILE: 'profile/UPDATE_PROFILE',
  DELETE_PROFILE: 'profile/DELETE_PROFILE',
  SET_BLOB: 'profile/SET_BLOB',
  RESET: 'profile/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IProfileMySuffix>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type ProfileMySuffixState = Readonly<typeof initialState>;

// Reducer

export default (state: ProfileMySuffixState = initialState, action): ProfileMySuffixState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PROFILE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PROFILE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PROFILE):
    case REQUEST(ACTION_TYPES.UPDATE_PROFILE):
    case REQUEST(ACTION_TYPES.DELETE_PROFILE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_PROFILE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PROFILE):
    case FAILURE(ACTION_TYPES.CREATE_PROFILE):
    case FAILURE(ACTION_TYPES.UPDATE_PROFILE):
    case FAILURE(ACTION_TYPES.DELETE_PROFILE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROFILE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_PROFILE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PROFILE):
    case SUCCESS(ACTION_TYPES.UPDATE_PROFILE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PROFILE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.SET_BLOB: {
      const { name, data, contentType } = action.payload;
      return {
        ...state,
        entity: {
          ...state.entity,
          [name]: data,
          [name + 'ContentType']: contentType,
        },
      };
    }
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/profiles';

// Actions

export const getEntities: ICrudGetAllAction<IProfileMySuffix> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PROFILE_LIST,
    payload: axios.get<IProfileMySuffix>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IProfileMySuffix> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PROFILE,
    payload: axios.get<IProfileMySuffix>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IProfileMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PROFILE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IProfileMySuffix> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PROFILE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IProfileMySuffix> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PROFILE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const setBlob = (name, data, contentType?) => ({
  type: ACTION_TYPES.SET_BLOB,
  payload: {
    name,
    data,
    contentType,
  },
});

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
